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
  const { options, status, error } = useSelector((state) => state.propertyType)

  useEffect(() => {
    if (status === 'idle') {
      dispatch(fetchPropertyTypeList())
    }
    console.log(options)
  })

  const handleChange = (event, value) => {
    onChange(value ? value.propertyTypeName : '')
  }
  return (
    <Autocomplete
      value={
        options
          ? options.find((option) => option.propertyTypeName === value) || null
          : null
      }
      options={options || []}
      getOptionLabel={(options) => options.label || ''}
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
