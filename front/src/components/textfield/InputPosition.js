const { TextField } = require('@mui/material')

const InputPosition = ({ value, onChange, sx }) => {
  return (
    <TextField
      sx={sx}
      value={value}
      onChange={onChange}
      name="position"
      label="Position"
      id="position"
      fullWidth
      variant="outlined"
      type="position"
    />
  )
}

InputPosition.displayName = 'InputPosition'

export default InputPosition
