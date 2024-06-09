const { TextField } = require('@mui/material')

const InputName = ({ value, onChange, sx }) => {
  return (
    <TextField
      value={value}
      onChange={onChange}
      sx={sx}
      name="name"
      label="Name"
      fullWidth
    />
  )
}

InputName.displayName = 'InputName'

export default InputName
