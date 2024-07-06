import { Button } from '@mui/material'

const DeleteBtn = ({ onClick }) => {
  return (
    <Button
      onClick={onClick}
      fullWidth
      variant="contained"
      sx={{
        backgroundColor: '#d32f2f',
        '&:hover': {
          backgroundColor: '#b71c1c',
        },
      }}
    >
      삭제
    </Button>
  )
}

DeleteBtn.displayName = 'DeleteBtn'

export default DeleteBtn
