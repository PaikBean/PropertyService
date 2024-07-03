const { Grid, Toolbar, Typography, Button } = require('@mui/material')

const AddDeleteToolabr = ({
  variant = 'h5',
  text,
  onAddClick,
  onDeleteClick,
}) => {
  return (
    <Grid container alignItems="center" justifyContent="space-between">
      <Grid item>
        <Toolbar>
          <Typography variant={variant}>{text}</Typography>
        </Toolbar>
      </Grid>
      <Grid item alignItems="end">
        <Grid container>
          <Grid item>
            <Button
              variant="contained"
              onClick={onAddClick}
              sx={{
                height: 35,
                marginBottom: '1.5px',
                backgroundColor: '#56866fec',
                '&:hover': {
                  backgroundColor: '#56866f',
                },
              }}
            >
              추가
            </Button>
          </Grid>
          <Grid item>
            <Button
              variant="contained"
              onClick={onDeleteClick}
              sx={{
                height: 35,
                marginBottom: '1.5px',
                backgroundColor: '#56866fec',
                '&:hover': {
                  backgroundColor: '#56866f',
                },
              }}
            >
              삭제
            </Button>
          </Grid>
        </Grid>
      </Grid>
    </Grid>
  )
}

AddDeleteToolabr.displayName = 'AddDeleteToolabr'

export default AddDeleteToolabr
