import { Autocomplete, TextField } from '@mui/material'

const Priority = ({ value, onChange, sx, readOnly = false }) => {
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
      renderInput={(params) => (
        <TextField
          {...params}
          label="중요도"
          InputProps={{
            ...params.InputProps,
            readOnly: readOnly,
          }}
        />
      )}
      disabled={readOnly}
      sx={sx}
    />
  )
}

Priority.displayName = 'Priority'

export default Priority
