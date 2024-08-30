// React, Next
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'

// Materials
import { Autocomplete, TextField } from '@mui/material'

// Custom Components

// Utils
import { fetchInflowTypeList } from '@/store/slices/inflowTypeSlice'

const InflowType = ({ value, onChange, label = 'Inflow Type',   sx,
  readOnly = false, }) => {
  const dispatch = useDispatch()
  //   const { options, status, error } = useSelector((state) => state.inflowType)
  const { options, status, error } = useSelector((state) => state.inflowType)

  useEffect(() => {
    if (status === 'idle') {
      dispatch(fetchInflowTypeList())
    }
  }, [dispatch, status])

  const handleChange = (event, value) => {
    onChange(value ? value.inflowTypeName : '')
  }
  return (
    <Autocomplete
      value={
        options
          ? options.find((option) => option.inflowTypeName === value) || null
          : null
      }
      options={options || []}
      getOptionLabel={(options) => options.label || ''}
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

InflowType.displayName = 'InflowType'
export default InflowType
