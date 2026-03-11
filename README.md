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

### KanbanTask를 활용한 유효성 검증 추가
- 다이얼로그 내부에 있던 유효성 검사 로직을 도메인 모델로 이동시켜 도메인 규칙을 모은다.
- 사용되는 정규식은 싱글톤으로 선언한다.

### TaskDialog 리팩터링
- 테스트 용이성을 위해 `TaskDialog`를 Stateful 컴포넌트와 Stateless 컴포넌트로 분리한다.

### 단위 테스트 및 UI 테스트 작성
- `KanbanTask`에 추가된 도메인 유효성 검증 로직(제목, 태그 형식 및 개수)에 대한 단위 테스트를 작성한다.
- `TaskDialogContent`를 활용하여 필수 입력 누락 시 또는 태그 형식이 올바르지 않을 때 생성 버튼이 비활성화되는지 Compose UI Testing을 진행한다.
