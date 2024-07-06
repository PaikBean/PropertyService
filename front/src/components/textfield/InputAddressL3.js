const { TextField } = require('@mui/material')

const InputADdressL3 = ({
  value,
  onChange,
  sx,
  readOnly = false,
  label = 'Address Level3',
}) => {
  return (
    <TextField
      sx={sx}
      value={value}
      onChange={onChange}
      label={label}
      id="address-level-3"
      required
      fullWidth
      variant="outlined"
      type="addressLevel3"
      autoComplete="addressLevel3"
      InputProps={{
        readOnly: readOnly, // readOnly 속성 설정
      }}
    />
  )
}

InputADdressL3.displayName = 'InputADdressL3'

export default InputADdressL3
