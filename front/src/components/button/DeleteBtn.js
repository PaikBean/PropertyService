import { Button } from '@mui/material'

const DeleteBtn = ({ onClick, label = '삭제' }) => {
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
      {label}
    </Button>
  )
}

DeleteBtn.displayName = 'DeleteBtn'

export default DeleteBtn
