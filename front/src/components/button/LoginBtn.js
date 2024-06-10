import { Button } from '@mui/material'

const LoginBtn = (sx) => {
  return (
    <Button
      type="submit"
      fullWidth
      variant="contained"
      sx={{
        mt: 3,
        mb: 2,
        backgroundColor: '#56866fec',
        '&:hover': {
          backgroundColor: '#56866f',
        },
      }}
    >
      Sign In
    </Button>
  )
}

LoginBtn.displayName = 'LoginBtn'

export default LoginBtn
