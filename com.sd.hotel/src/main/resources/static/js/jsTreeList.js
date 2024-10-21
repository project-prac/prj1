
$(document).ready(function() {

  // 컨텍스트 경로 추출함수



  let jsTreeData = [];
  let roomList;
  let roomImgList;
  let roomDetailList;

  fetch('/admin/room/roomList.do', {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    }
  })

    .then(response => response.json())
    .then(data => {

      roomList = data.roomList;
      roomImgList = data.roomImgList;
      roomDetailList = data.roomDetailList; //detailRoom의 수


      // 각 roomNo에 연결된 roomDetail의 개수를 계산
      const roomDetailCountMap = roomDetailList.reduce((acc, detail) => {
        const roomNo = detail.roomNo;
        if (!acc[roomNo]) {
          acc[roomNo] = 0;
        }
        acc[roomNo]++;
        return acc;
      }, {});

      jsTreeData = roomList.map(room => {
        const detailCount = roomDetailCountMap[room.roomNo] || 0;
        return {
          id: room.roomNo,
          parent: room.depth === 0 ? '#' : roomList.find(r => r.roomNo === Math.floor(room.roomNo / 100) * 100).roomNo,
          text: room.depth === 0 ? room.roomName : `${room.roomName} (${detailCount})`,
          roomName: room.roomName,
          roomNum: `${detailCount}`,
          state: { opened: false }
        }
      });
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
    const roomImg = roomImgList.filter(room => room.roomNo == selectedRoomId);

    if (data.node.parent !== '#') {
      if (selectedRoom) {
        $('#roomNo').val(selectedRoom.id);
        $('#roomName').val(selectedRoom.roomName);
        $('#form-roomNum').val(selectedRoom.roomNum);
      }
      if (roomDetail) {
        $('#info').val(roomDetail.info);
        $('#price').val(roomDetail.price);
        $('#people').val(roomDetail.people);
      }
      roomInfo.style.display = "block";
    }

    //이미지 추가
    const roomImgContainer = document.querySelector('.roomImgLists');
    roomImgContainer.innerHTML = '';  // 기존 이미지 초기화


    if (!roomImg || roomImg.length === 0) {
      roomImgContainer.innerHTML = '<p>No images available</p>';  // 이미지가 없을 때 메시지
    } else {
      roomImg.forEach(img => {

        const imgElement = document.createElement('img');
        imgElement.src = `${img.uploadPath}/${img.filesystemName}`; // 경로 수정        imgElement.style.width = '100px'; // 이미지 크기 설정 (원하는 대로 조정)
        imgElement.style.height = '100px'; // 이미지 크기 설정 (원하는 대로 조정)
        imgElement.style.marginRight = '10px'; // 이미지 간격 설정
        roomImgContainer.appendChild(imgElement);
      });
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
      str += ` <option value="${room.roomNo}">${room.roomNo} ${room.roomName}</option>`;
    })

    roomNoSelect.append(str);

    let selectedValue = roomNoSelect.val();
    $('#parent-Name').val(selectedValue)


    roomNoSelect.change(function() {
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

    var formData = new FormData(this); // FormData 객체 생성 (this는 폼)


    allFiles.forEach(function(file) {
      formData.append('files', file); // 'files'는 서버에서 받을 이름으로 설정
    });

    for (var pair of formData.entries()) {
      if (pair[1] instanceof File) {
        console.log(pair[0] + ': ' + pair[1].name + ', size: ' + pair[1].size + ' bytes'); // 파일 이름과 크기를 출력
      } else {
        console.log(pair[0] + ': ' + pair[1]); // 텍스트 데이터는 그대로 출력
      }
    }

    console.log(formData);

    $.ajax({
      type: 'POST',
      url: '/admin/room/roomTypeRegister.do', // 요청할 URL
      data: formData, // FormData 전송
      contentType: false, // FormData 사용 시 반드시 false로 설정
      processData: false, // FormData 사용 시 반드시 false로 설정
      success: function(response, data) {
        console.log(data);
        console.log("response!!", response);
        if (response.success) {
          alert('객실이 추가되었습니다.');
          location.reload();
        } else {
          alert('객실 추가에 실패했습니다. 다시 시도해주세요.');
          location.reload();
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



  let filesBtn = document.getElementById('files-button');
  let filesInput = document.getElementById('files-input');
  let fileLists = document.querySelector('#file-lists');
  let allFiles = []; // 모든 파일을 저장하는 배열
  const MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
  const MAX_FILE_COUNT = 4; // 최대 파일 개수

  filesBtn.addEventListener('click', function(event) {
    filesInput.click(); // input 요소를 클릭
  });

  filesInput.addEventListener('change', function(event) {
    // 선택된 파일을 추가
    addFiles(Array.from(filesInput.files));
    // 파일 선택 후 input 필드 초기화
    filesInput.value = ''; // 이 부분을 추가하여 중복 선택 방지
  });



  // 드래그 앤 드롭 이벤트 처리
  const fileArea = document.getElementById('file-area');

  fileLists.addEventListener('dragover', function(event) {
    event.preventDefault(); // 기본 동작 방지
    fileArea.style.border = '2px dashed #000'; // 드래그 중 스타일 변경
  });

  fileLists.addEventListener('dragleave', function(event) {
    event.preventDefault();
    fileArea.style.border = ''; // 드래그 나가면 스타일 복원
  });

  fileLists.addEventListener('drop', function(event) {
    event.preventDefault(); // 기본 동작 방지
    fileArea.style.border = ''; // 드래그 나가면 스타일 복원

    const droppedFiles = Array.from(event.dataTransfer.files); // 드래그 앤 드롭된 파일들
    addFiles(droppedFiles); // 드래그된 파일 추가
  });

  function addFiles(newFiles) {
    // 기존 파일 크기와 새로 추가할 파일 크기 합산
    let totalSize = allFiles.reduce((sum, file) => sum + file.size, 0);

    // 경고 메시지가 한 번만 뜨도록 하기 위해 상태 변수 설정
    let hasExceededCount = false;
    let hasExceededSize = false;
    let hasDuplicateFile = false;

    newFiles.forEach(file => {
      // 중복된 파일 확인
      if (allFiles.some(f => f.name === file.name && f.size === file.size)) {
        hasDuplicateFile = true;
        return;
      }

      // 총 파일 개수가 4개를 초과하지 않도록 제한
      if (allFiles.length >= MAX_FILE_COUNT) {
        hasExceededCount = true;
        return;
      }

      // 각 파일 크기가 10MB를 넘지 않도록 체크
      if (file.size + totalSize > MAX_FILE_SIZE) {
        hasExceededSize = true;
        return;
      }

      // 중복 파일이 없고 조건을 만족하는 경우에만 추가
      allFiles.push(file);
      totalSize += file.size; // 총 파일 크기 업데이트
    });

    // 경고 메시지 한 번만 출력
    if (hasDuplicateFile) {
      alert('중복된 파일은 업로드할 수 없습니다.');
    }
    if (hasExceededCount) {
      alert('최대 4개의 파일만 업로드할 수 있습니다.');
    }
    if (hasExceededSize) {
      alert('전체 파일 크기는 10MB를 초과할 수 없습니다.');
    }

    displayFiles(); // 파일 목록 갱신
  }

  function displayFiles() {
    fileLists.innerHTML = ''; // 기존 목록 초기화

    // 전체 파일 목록을 표시
    allFiles.forEach((file, index) => {
      let fileItem = document.createElement('div');
      fileItem.textContent = `${index + 1}. ${file.name} (${(file.size / 1024 / 1024).toFixed(2)} MB)`; // 파일 이름과 크기 표시

      // 'x' 삭제 버튼 생성
      let removeBtn = document.createElement('button');
      removeBtn.textContent = 'x';
      removeBtn.style.marginLeft = '10px';

      // 'x' 버튼 클릭 이벤트 처리
      removeBtn.addEventListener('click', function() {
        removeFile(index); // 해당 파일을 삭제
      });

      // 파일 이름과 삭제 버튼을 함께 표시
      fileItem.appendChild(removeBtn);
      fileLists.appendChild(fileItem);
    });
  }

  function removeFile(index) {
    allFiles.splice(index, 1); // 해당 인덱스의 파일을 배열에서 삭제
    displayFiles(); // 파일 목록 갱신
  }


  /*객실수정*/
  $('#room-info').on('submit', function(event) {
    $.ajax({
      type:'post',
      url: '/admin/room/roomModify.do',
      data: formData, // FormData 전송
      contentType: false, // FormData 사용 시 반드시 false로 설정
      processData: false,
      success:(resData) =>{
        
      } 
    })
    
    
    
  })



})



