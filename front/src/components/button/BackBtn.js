import { Button } from '@mui/material'

const BackBtn = ({ onClick, label = '이전' }) => {
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

BackBtn.displayName = 'BackBtn'

export default BackBtn
