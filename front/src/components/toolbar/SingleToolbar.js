const { Grid, Toolbar, Typography, Button } = require('@mui/material')

const SingleToolbar = ({ text }) => {
  return (
    <Grid container alignItems="center" justifyContent="space-between">
      <Grid item>
        <Toolbar>
          <Typography variant="h5">{text}</Typography>
        </Toolbar>
      </Grid>
    </Grid>
  )
}

SingleToolbar.displayName = 'SingleToolbar'

export default SingleToolbar
