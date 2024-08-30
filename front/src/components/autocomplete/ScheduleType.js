// React, Next
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'

// Materials
import { Autocomplete, TextField } from '@mui/material'

// Custom Components

// Utils
import { fetchScheduleTypeList } from '@/store/slices/scheduleTypeSlice'

const ScheduleType = ({ value, onChange, sx, readOnly = false }) => {
  const dispatch = useDispatch()
  const { options, status, error } = useSelector((state) => state.scheduleType)

  useEffect(() => {
    if (status === 'idle') {
      dispatch(fetchScheduleTypeList())
    }
  }, [dispatch, status])

  const handleChange = (event, value) => {
    onChange(value ? value.scheduleTypeName : '')
  }
  return (
    <Autocomplete
      value={
        options
          ? options.find((option) => option.scheduleTypeName === value) || null
          : null
      }
      options={options || []}
      getOptionLabel={(options) => options.label || ''}
      onChange={handleChange}
      renderInput={(params) => (
        <TextField
          {...params}
          label="일정 유형"
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

ScheduleType.displayName = 'ScheduleType'
export default ScheduleType
