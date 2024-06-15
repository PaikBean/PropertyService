import { Button } from '@mui/material'

const SaveBtn = ({ onClick }) => {
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
      저장
    </Button>
  )
}

SaveBtn.displayName = 'SaveBtn'

export default SaveBtn
