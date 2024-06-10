const { TextField } = require('@mui/material')

const InputName2 = ({ label, value, onChange, sx }) => {
  return (
    <TextField
      label={label}
      value={value}
      onChange={onChange}
      sx={sx}
      name="name"
      fullWidth
    />
  )
}

InputName2.displayName = 'InputName2'

export default InputName2
