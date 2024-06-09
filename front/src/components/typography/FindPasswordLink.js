import { Typography } from '@mui/material'

const FindPasswordLink = () => {
  return (
    <Typography variant="body2" color="text.secondary" align="center">
      <a
        href="/find-password"
        style={{
          color: 'inherit', // 부모 Typography의 색상을 상속받습니다.
          textDecoration: 'none', // 밑줄을 제거합니다.
        }}
      >
        {'Forgot password?'}
      </a>
    </Typography>
  )
}

FindPasswordLink.displayName = 'FindPasswordLink'

export default FindPasswordLink
