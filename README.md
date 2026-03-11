## 🚀 1단계 - 칸반 보드 생성(상품 목록)

안드로이드 8기 레벨 1 미션, 칸반 보드 생성미션을 관리하는 프로젝트입니다.

## 1단계 기능 구현 목록

### TaskDialogTopAppBar() 구현
- 제목과 닫기 버튼을 Row로 묶어서 구현한다.

### TaskFieldLabel() 구현
- Text() 를 사용한다.
- isRequired 파라미터를 사용한다.
  - isRequired에 따라서 "*"를 노출한다.

### TaskDialogTextField() 구현
- BasicTextField()를 사용한다.
- isError 파라미터를 가진다.

### TaskOptionCard() 구현
- Box() 레이아웃을 사용한다.
- content 파라미터를 뚫는다.

### StatusOptionCard(), AssigneeOptionCard() 구현
- TaskOptionCard()를 래핑하는 컴포넌트를 구현한다.
- Text는 Text 정보만 표시한다.
- Assignee는 작성자의 사진과 이름을 표시한다.

### TaskDialogButton() 구현
- Box() 레이아웃을 사용한다.
- isEnabled 파라미터를 가진다.

### TaskDialog() 구현
- 여러 컴포넌트를 조합한 새 태스크 생성 다이얼로그를 구현한다.
