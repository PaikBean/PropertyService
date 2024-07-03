import { fetchScheduleTypeList } from '@/store/slices/scheduleTypeSlice'
import { Autocomplete, TextField } from '@mui/material'
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'

const ScheduleType = ({ value, onChange }) => {
  const dispatch = useDispatch()
  const { options, status, error } = useSelector((state) => state.scheduleType)

  useEffect(() => {
    if (status === 'idle') {
      dispatch(fetchScheduleTypeList())
    }
  }, [dispatch, status])

  const handleChange = (event, value) => {
    onChange(value ? value.scheduleId : '')
  }
  return (
    <Autocomplete
      value={
        options
          ? options.find((option) => option.scheduleId === value) || null
          : null
      }
      options={options || []}
      getOptionLabel={(options) => options.scheduleType || ''}
      onChange={handleChange}
      renderInput={(params) => <TextField {...params} label="일정 유형" />}
    />
  )
}

ScheduleType.displayName = 'ScheduleType'
export default ScheduleType
