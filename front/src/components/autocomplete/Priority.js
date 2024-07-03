import { Autocomplete, TextField } from '@mui/material'

const Priority = ({ value, onChange }) => {
  const priorityOptions = [
    { label: '상', value: 'HIGH' },
    { label: '중', value: 'MEDIUM' },
    { label: '하', value: 'LOW' },
  ]

  return (
    <Autocomplete
      options={priorityOptions}
      getOptionLabel={(option) => option.label}
      value={priorityOptions.find((option) => option.value === value) || null}
      onChange={(event, newValue) => {
        onChange(newValue ? newValue.value : null)
      }}
      renderInput={(params) => <TextField {...params} label="중요도" />}
    />
  )
}

Priority.displayName = 'Priority'

export default Priority
