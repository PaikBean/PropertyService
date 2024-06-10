import { Typography } from '@mui/material'

const SilhouetteLicense = () => {
  return (
    <Typography
      variant="caption"
      color="text.secondary"
      sx={{
        position: 'absolute', // 텍스트를 절대 위치로 설정하여 부모 내에서 자유롭게 이동할 수 있게 합니다.
        bottom: 10, // 하단에서 10px 떨어진 곳에 위치합니다.
        right: 16, // 우측에서 16px 떨어진 곳에 위치합니다.
        padding: '4px', // 텍스트 주위에 약간의 패딩을 추가합니다.
        a: {
          color: 'inherit', // 부모 Typography의 색상을 상속받습니다.
          textDecoration: 'none', // 밑줄을 제거합니다.
        },
      }}
    >
      <a
        href="http://www.freepik.com"
        target="_blank"
        rel="noopener noreferrer"
      >
        Designed by rawpixel.com / Freepik
      </a>
    </Typography>
  )
}

SilhouetteLicense.displayName = 'SilhouetteLicense'

export default SilhouetteLicense
