const { Grid, Toolbar, Typography, Button } = require('@mui/material')

const SaveToolbar = ({ text, onClick }) => {
  return (
    <Grid container alignItems="center" justifyContent="space-between">
      <Grid item>
        <Toolbar>
          <Typography variant="h5">{text}</Typography>
        </Toolbar>
      </Grid>
      <Grid item alignItems="end">
        <Grid container>
          <Button
            variant="contained"
            onClick={onClick}
            sx={{
              height: 35,
              marginBottom: '1.5px',
              backgroundColor: '#56866fec',
              '&:hover': {
                backgroundColor: '#56866f',
              },
            }}
          >
            저장
          </Button>
        </Grid>
      </Grid>
    </Grid>
  )
}

SaveToolbar.displayName = 'SaveToolbar'

export default SaveToolbar
