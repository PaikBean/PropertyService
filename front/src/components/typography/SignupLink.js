import { Typography } from '@mui/material'

const SignUpLink = () => {
  return (
    <Typography variant="body2" color="text.secondary" align="center">
      <a
        href="/sign-up"
        style={{
          color: 'inherit', // 부모 Typography의 색상을 상속받습니다.
          textDecoration: 'none', // 밑줄을 제거합니다.
        }}
      >
        {"Don't have an account? Sign Up"}
      </a>
    </Typography>
  )
}
SignUpLink.displayName = 'SignUpLink'

export default SignUpLink
