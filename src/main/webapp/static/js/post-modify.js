/**
 * 
 */
document.addEventListener('DOMContentLoaded', function(){
    
    // form 요소를 찾음
    const modifyForm = document.querySelector('#postModifyForm');
    
    // input안의 요소 찾음     
    const inputId = document.querySelector('input#id');
    const inputTitle = document.querySelector('input#title');
    const textareaContent = document.querySelector('textarea#content');
    
    //수정 완료 버튼 찾음
    const btnUpdate = document.querySelector('button#btnUpdate');
    //삭제 버튼 찾음
    const btnDelete = document.querySelector('button#btnDelete');
    
    // 삭제 버튼에 클릭 이벤트 리스너를 등록.
    btnDelete.addEventListener('click', function(e){
        
        e.preventDefault();// 버튼의 기본동작이 기능하지 않도록 하겠다.
        //-> form안에 있는 버튼 또는 input의 기본 동작은
        // 폼의 내용을 서버로 제출(서버로 요청을 보냄).
        
        const id = inputId.value;
        
        const result = confirm(`NO.${id} 정말 삭제 할까요?`);
        
        console.log(`삭제확인 결과 = ${result}`);
        
        // 사용자가 confirm 창에서 확인을 클릭했을 때.
        
        if(result){
            // delete 요청을 함
            modifyForm.action = 'delete';// 폼 제출 요청 주소
            modifyForm.method = 'post'; // 요청 방식
            modifyForm.submit();// 요청 보내기
        }else{
                                
        }
    });
    
    
    btnUpdate.addEventListener('click', function(e){
        
        e.preventDefault();// form 제출 기능을 비활성화
        
        const id = inputId.value;
        const title = inputTitle.value;
        const content = textareaContent.value;
        
        if( title === '' || content ==='' ){ /*타입까지 체크*/
            alert('제목과 내용은 반드시 입력해야 합니다.');
            return ;
        }
        
        const result = confirm(`No.${id} 포스트를 수정 할까요?`);
        if(result){
          modifyForm.action = './modify';// 업데이트 요청 주소
          modifyForm.method = 'post';// 요청 방식
          modifyForm.submit();  // 폼 제출 요청 보내기
        } 

    });
       
});