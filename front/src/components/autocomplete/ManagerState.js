import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { Autocomplete, TextField } from '@mui/material'
import { fetchManagerStateList } from '@/store/slices/managerStateSlice'

const ManagerState = ({ value, onChange, label = '근무 상태' }) => {
  const dispatch = useDispatch()
  const { options, status, error } = useSelector((state) => state.managerState)

  useEffect(() => {
    if (status === 'idle') {
      dispatch(fetchManagerStateList())
    }
  }, [dispatch, status])

  const handleChange = (event, value) => {
    onChange(value ? value.managerStateId : '')
  }

  return (
    <Autocomplete
      value={
        options
          ? options.find((option) => option.managerStateId === value) || null
          : null
      }
      options={options || []}
      getOptionLabel={(option) => option.managerState || ''}
      onChange={handleChange}
      renderInput={(params) => <TextField {...params} label={label} />}
    />
  )
}

ManagerState.displayName = 'ManagerState'

export default ManagerState
