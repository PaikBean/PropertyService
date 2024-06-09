import { Autocomplete, TextField } from '@mui/material'

const Gender = ({ value, onChange }) => {
  const genderOptions = [
    { label: 'Male', value: 'MALE' },
    { label: 'Female', value: 'FEMALE' },
  ]

  return (
    <Autocomplete
      options={genderOptions}
      getOptionLabel={(option) => option.label}
      value={genderOptions.find((option) => option.value === value) || null}
      onChange={(event, newValue) => {
        onChange(newValue ? newValue.value : null)
      }}
      renderInput={(params) => <TextField {...params} label="Gender" />}
    />
  )
}

Gender.displayName = 'Gender'

export default Gender
