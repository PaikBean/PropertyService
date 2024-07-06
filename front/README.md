# Property Service - Front Server

PropertyService 프로젝트에 구성된 프론트 엔드 서버입니다.

## 목차

- [필수 조건](#필수-조건)
- [시작하기](#시작하기)
- [사용 가능한 스크립트](#사용-가능한-스크립트)
- [의존성](#의존성)
- [개발 의존성](#개발-의존성)
- [버전에 대한 노트](#버전에-대한-노트)

## 필수 조건

다음이 요구사항이 설치되어 있는지 확인하세요:

- Node.js (권장 버전 14.x 이상)
- npm (권장 버전 6.x 이상)

## 시작하기

1. **저장소를 클론합니다:**

   ```sh
   git clone https://github.com/JH22222/PropertyService.git
   cd front
   ```

   > 현재 작업 진행 중인 결과물을 원한다면 develop 브랜치로 이동 후 하위 작업 진행하시기 바랍니다. (2024.07.06)

2. **의존성을 설치합니다:**

   ```sh
   npm install
   ```

3. **개발 서버를 실행합니다:**
   ```sh
   npm run dev
   ```
   이제 [http://localhost:3000](http://localhost:3000)에서 애플리케이션이 실행 중인지 확인할 수 있습니다.

## 사용 가능한 스크립트

프로젝트 디렉토리에서 다음 스크립트를 실행할 수 있습니다:

### `npm run dev`

개발 모드에서 앱을 실행합니다. 브라우저에서 [http://localhost:3000](http://localhost:3000) 을 열어 확인할 수 있습니다. 페이지를 수정하면 자동으로 다시 로드됩니다. 또한 콘솔에서 린트 오류를 확인할 수 있습니다.

### `npm run build`

프로덕션용으로 앱을 `.next` 폴더에 빌드합니다. React를 프로덕션 모드로 번들링하고 최적화하여 최고의 성능을 발휘합니다.

### `npm run start`

프로덕션 서버를 시작합니다. `npm run start`를 실행하기 전에 `npm run build`를 실행해야 합니다.

### `npm run lint`

ESLint를 실행하여 코드의 잠재적인 오류를 분석하고 코딩 표준을 적용합니다.

## 의존성

이 프로젝트에서 사용된 의존성 목록은 다음과 같습니다:

- `@emotion/cache`: ^11.11.0
- `@emotion/react`: ^11.11.4
- `@emotion/server`: ^11.11.0
- `@emotion/styled`: ^11.11.5
- `@fullcalendar/daygrid`: ^6.1.14
- `@fullcalendar/interaction`: ^6.1.14
- `@fullcalendar/react`: ^6.1.14
- `@fullcalendar/timegrid`: ^6.1.14
- `@mui/icons-material`: ^5.15.19
- `@mui/material`: ^5.15.19
- `@mui/material-nextjs`: ^5.15.11
- `@mui/x-data-grid`: ^7.7.0
- `@mui/x-date-pickers`: ^7.7.0
- `@reduxjs/toolkit`: ^2.2.5
- `dayjs`: ^1.11.11
- `next`: 14.2.3
- `react`: ^18
- `react-dom`: ^18
- `react-redux`: ^9.1.2
- `styled-components`: ^6.1.11

## 개발 의존성

이 프로젝트에서 사용된 개발 의존성 목록은 다음과 같습니다:

- `eslint`: ^8
- `eslint-config-next`: 14.2.3

## 버전에 대한 노트

- **Next.js**: 프로젝트는 Next.js 버전 14.2.3을 사용합니다. 최신 기능과 업데이트가 포함되어 있습니다. 다른 라이브러리와의 호환성을 확인하세요.
- **React**: 이 프로젝트는 React 버전 18을 사용합니다. 컴포넌트와 훅이 이 버전과 호환되는지 확인하세요.
- **MUI (Material-UI)**: Next.js와 통합 시 MUI 문서를 참고하여 버전 호환성을 확인하세요.
- **FullCalendar**: 프로젝트는 FullCalendar 버전 6.1.14를 사용합니다. 사용법 및 통합에 대한 자세한 내용은 FullCalendar 문서를 참조하세요.

모든 의존성이 지정된 버전과 호환되는지 확인하여 충돌이나 문제가 발생하지 않도록 하세요.

## 결론

이제 Next.js 14 프론트 엔드 서버를 시작하는 데 필요한 모든 정보를 얻었습니다. 문제가 발생하거나 추가 질문이 있는 경우, 프론트엔드 담당자 jh2going@gmail.com 으로 문의주시기 바랍니다.
