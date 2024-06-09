const { TextField } = require('@mui/material')

const InputManagerCode = ({ value, onChange, sx }) => {
  return (
    <TextField
      sx={sx}
      value={value}
      onChange={onChange}
      name="managerCode"
      label="Manager Code"
      id="manager-code"
      fullWidth
      variant="outlined"
      type="managerCode"
    />
  )
}

InputManagerCode.displayName = 'InputManagerCode'

export default InputManagerCode
