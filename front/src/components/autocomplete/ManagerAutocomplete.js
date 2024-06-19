import { fetchManagerList } from '@/store/slices/managerSlice'
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'

const { Autocomplete, TextField } = require('@mui/material')

const ManagerAutocomplete = ({ value, onChange }) => {
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
      renderInput={(params) => <TextField {...params} label="Manager" />}
    />
  )
}

ManagerAutocomplete.displayName = 'ManagerAutocomplete'

export default ManagerAutocomplete
