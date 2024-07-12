// React, Next
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'

// Materials
import { Autocomplete, TextField } from '@mui/material'

// Custom Components

// Utils
import { fetchPropertyTypeList } from '@/store/slices/propertyTypeSlice'

const PropertyType = ({ value, onChange, sx, readOnly = false }) => {
  const dispatch = useDispatch()
  const { options, status, error } = useSelector((state) => state.PropertyType)

  useEffect(() => {
    if (status === 'idle') {
      dispatch(fetchPropertyTypeList())
    }
  })

  const handleChange = (event, value) => {
    onChange(value ? value.propertyTypeId : '')
  }
  return (
    <Autocomplete
      value={
        options
          ? options.find((option) => option.propertyTypeId === value) || null
          : null
      }
      options={options || []}
      getOptionLabel={(options) => options.PropertyType || ''}
      onChange={handleChange}
      renderInput={(params) => (
        <TextField
          {...params}
          label="주용도"
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

PropertyType.displayName = 'PropertyType'

export default PropertyType
