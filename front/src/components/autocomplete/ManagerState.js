import { fetchManagerStateList } from '@/store/slices/managerStateSlice'
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'

const { Autocomplete, TextField } = require('@mui/material')

const ManagerState = ({ onChange }) => {
  const dispatch = useDispatch()
  const { options, status, error } = useSelector((state) => state.managerState)

  useEffect(() => {
    if (status === 'idle') {
      dispatch(fetchManagerStateList())
    }
  })

  const handleChange = (event, value) => {
    onChange(value ? value.mangerStateId : '')
  }

  return (
    <Autocomplete
      options={options || []}
      getOptionLabel={(options) => options.managerState || ''}
      onChange={handleChange}
      renderInput={(params) => <TextField {...params} label="Manager Steate" />}
    />
  )
}

ManagerState.displayName = 'ManagerState'

export default ManagerState
