import { Button } from '@mui/material'

const SearchCompanyBtn = ({ onClick }) => {
  return (
    <Button
      onClick={onClick}
      fullWidth
      variant="contained"
      sx={{
        mt: 3,
        mb: 3,
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

SearchCompanyBtn.displayName = 'LoginBtn'

export default SearchCompanyBtn
