import { Button } from '@mui/material'

const SubmitBtn = ({ onClick, label = '등록' }) => {
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

SubmitBtn.displayName = 'SubmitBtn'

export default SubmitBtn
