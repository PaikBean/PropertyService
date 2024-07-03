const {
  Grid,
  Toolbar,
  Typography,
  Button,
  FormControlLabel,
  Switch,
} = require('@mui/material')

const SaveTogleToolbar = ({ text, onChange, onClick }) => {
  return (
    <Grid container alignItems="center" justifyContent="space-between">
      <Grid item>
        <Toolbar>
          <Typography variant="h5">{text}</Typography>
        </Toolbar>
      </Grid>
      <Grid item alignItems="end">
        <Grid container>
          <FormControlLabel
            sx={{
              display: 'block',
            }}
            control={<Switch onChange={onChange} color="primary" />}
          />
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

SaveTogleToolbar.displayName = 'SaveTogleToolbar'

export default SaveTogleToolbar
