// React, Next
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'

// Materials
const { Autocomplete, TextField } = require('@mui/material')

// Custom Components

// Utils
import { fetchManagerList } from '@/store/slices/managerSlice'

const ManagerAutocomplete = ({
  label = 'Manager',
  value,
  onChange,
  sx,
  readOnly = false,
}) => {
  const dispatch = useDispatch()
  const { options, status, error } = useSelector((state) => state.manager)

  useEffect(() => {
    if (status === 'idle') {
      dispatch(fetchManagerList())
    }
  }, [dispatch, status])

  const handleChange = (event, value) => {
    onChange(value ? value.managerId : '')
  }

  return (
    <Autocomplete
      value={
        options
          ? options.find((option) => option.managerId === value) || null
          : null
      }
      options={options || []}
      getOptionLabel={(options) => options.managerName || ''}
      onChange={handleChange}
      renderInput={(params) => (
        <TextField
          {...params}
          label={label}
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

ManagerAutocomplete.displayName = 'ManagerAutocomplete'

export default ManagerAutocomplete
