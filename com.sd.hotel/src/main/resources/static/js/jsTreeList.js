
$(document).ready(function() {

  let jsTreeData = [];
  let roomList;
  fetch('/admin/room/roomList.do', {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    }
  })

    .then(response => response.json())
    .then(data => {
     // console.log(data)
      roomList = data.roomList;

      jsTreeData = roomList.map(room => ({
        id: room.roomNo,
        parent: room.depth === 0 ? '#' : roomList.find(r => r.roomNo === Math.floor(room.roomNo / 100) * 100).roomNo,
        text: room.roomName,
        state: { opened: false }
      }))
     // console.log(jsTreeData)

      // jstree Start~~
      $('#jstree').jstree({
        'core': {
          'data': jsTreeData
        }
      });
      // jstree End~~
      roomNoOptions(roomList);
      roomNoOptions2(roomList);
      
      
    })
  // jsTree node클릭시 정보open
  $('#jstree').on('select_node.jstree', function(e, data) {
    //console.log(jsTreeData)
    var roomInfo = document.getElementById("room-info");
    //console.log(data.node.id)

    const selectedRoomId = data.node.id;
    const selectedRoom = jsTreeData.find(room => room.id == selectedRoomId);
    const roomDetail = roomList.find(room => room.roomNo == selectedRoomId);

    if (data.node.parent !== '#') {
      if (selectedRoom) {
        $('#roomNo').val(selectedRoom.id);
        $('#roomName').val(selectedRoom.text);
        roomInfo.style.display = "block";
      }
      if (roomDetail) {
        $('#info').val(roomDetail.info);
        $('#price').val(roomDetail.price);
        $('#people').val(roomDetail.people);
      }

    }

  });

  function roomNoOptions(roomList) {
    const roomNoList = [100, 200, 300, 400, 500, 600, 700, 800, 900];
    const existRoomNos = roomList.map(room => Math.floor(room.roomNo / 100) * 100);

    const roomNoSelect = $('#roomNo-list');
    roomNoSelect.empty();

    roomNoList.forEach(roomNo => {
      if (!existRoomNos.includes(roomNo)) {
        roomNoSelect.append(`<option value="${roomNo}">${roomNo}</option>`);
      }
    })
  }


  /*객실유형 form 쪽*/
  function roomNoOptions2(roomList) {
    
    let roomNoList = roomList.filter((room) => room.parentName == '#')
    
    const roomNoSelect = $('#roomNo-list2');
    roomNoSelect.empty();
      
    let str = '';
    roomNoList.forEach(room => {
       str+= ` <option value="${room.roomNo}">${room.roomNo} ${room.roomName}</option>`;
    })
  
    roomNoSelect.append(str);
    
    let selectedValue = roomNoSelect.val();
    $('#parent-Name').val(selectedValue)
     
     
     
    roomNoSelect.change(function(){
      selectedValue = $(this).val();
      $('#parent-Name').val(selectedValue)
    })

  }

  

  $('.room-area-header').click(function() {
    $(this).next('form').stop(true, true).slideToggle(200);
  })


  /**/
  $('#roomTypeRegisterForm').on('submit', function(event) {
  
    event.preventDefault(); // 기본 제출 방지
    
    console.log("start")
    
    $.ajax({
        type: 'POST',
        url: '/admin/room/roomTypeRegister.do', // 요청할 URL
        data: $(this).serialize(), // 폼 데이터 직렬화
        success: function(response,data) {
          console.log(data);
          console.log("response!!",response);
            if (response.success) {
                alert('객실이 추가되었습니다.');
            } else {
                alert('객실 추가에 실패했습니다.');
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
          console.log(textStatus);
          console.log(errorThrown)
          console.log(jqXHR)
            alert('서버 오류가 발생했습니다.');
        }
    });

  
  });




})



