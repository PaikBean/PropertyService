import { Button } from '@mui/material'

const NextBtn = ({ onClick, label = '다음' }) => {
  return (
    <Button
      onClick={onClick}
      fullWidth
      variant="contained"
      sx={{
        backgroundColor: '#56866fec',
        '&:hover': {
          backgroundColor: '#56866f',
        },
      }}
    >
      {label}
    </Button>
  )
}

NextBtn.displayName = 'NextBtn'

export default NextBtn
