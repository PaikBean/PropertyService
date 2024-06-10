const { TextField } = require('@mui/material')

const InputADdressL3 = ({ value, onChange, sx }) => {
  return (
    <TextField
      sx={sx}
      value={value}
      onChange={onChange}
      label="Address Level 3"
      id="address-level-3"
      required
      fullWidth
      variant="outlined"
      type="addressLevel3"
      autoComplete="addressLevel3"
    />
  )
}

InputADdressL3.displayName = 'InputADdressL3'

export default InputADdressL3
