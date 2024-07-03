const { TextField } = require('@mui/material')

const InputName2 = ({
  label,
  value,
  onChange,
  name = 'name',
  sx,
  readOnly = false,
}) => {
  return (
    <TextField
      label={label}
      value={value}
      onChange={onChange}
      sx={sx}
      name={name}
      InputProps={{
        readOnly: readOnly, // readOnly 속성 설정
      }}
      fullWidth
    />
  )
}

InputName2.displayName = 'InputName2'

export default InputName2
