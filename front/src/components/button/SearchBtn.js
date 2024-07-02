import { Button } from '@mui/material'

const SearchBtn = ({ onClick, height }) => {
  return (
    <Button
      onClick={onClick}
      fullWidth
      variant="contained"
      sx={{
        height: height,
        backgroundColor: '#56866fec',
        '&:hover': {
          backgroundColor: '#56866f',
        },
      }}
    >
      검색
    </Button>
  )
}

SearchBtn.displayName = 'SearchBtn'

export default SearchBtn
