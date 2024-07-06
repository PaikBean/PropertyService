import DeleteBtn from '../button/DeleteBtn'

const {
  Grid,
  Toolbar,
  Typography,
  Button,
  FormControlLabel,
  Switch,
} = require('@mui/material')

const SaveDeleteTogleToolbar = ({ text, onChange, onDelete, onSave, mode }) => {
  return (
    <Grid container alignItems="center" justifyContent="space-between">
      <Grid item>
        <Toolbar>
          <Typography variant="h5">{text}</Typography>
        </Toolbar>
      </Grid>
      <Grid item>
        <Grid container alignItems="center" spacing={2}>
          {!mode && (
            <Grid item>
              <Button
                onClick={onDelete}
                variant="contained"
                sx={{
                  height: 35,
                  backgroundColor: '#d32f2f',
                  '&:hover': {
                    backgroundColor: '#b71c1c',
                  },
                }}
              >
                삭제
              </Button>
            </Grid>
          )}
          <Grid item>
            <FormControlLabel
              control={<Switch onChange={onChange} color="primary" />}
              sx={{
                marginRight: 0,
              }}
            />
          </Grid>
          <Grid item>
            <Button
              variant="contained"
              onClick={onSave}
              sx={{
                height: 35,
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
    </Grid>
  )
}

SaveDeleteTogleToolbar.displayName = 'SaveDeleteTogleToolbar'

export default SaveDeleteTogleToolbar
