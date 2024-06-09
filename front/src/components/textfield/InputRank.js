const { TextField } = require('@mui/material')

const InputRank = ({ value, onChange, sx }) => {
  return (
    <TextField
      sx={sx}
      value={value}
      onChange={onChange}
      name="rank"
      label="Rank"
      fullWidth
      id="rank"
      variant="outlined"
      type="Rank"
    />
  )
}

InputRank.displayName = 'InputRank'

export default InputRank
