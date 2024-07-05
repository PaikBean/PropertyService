import { fetchPropertyTypeList } from '@/store/slices/propertyTypeSlice'
import { Autocomplete, TextField } from '@mui/material'
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'

const PropertyType = ({ value, onChange }) => {
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
      renderInput={(params) => <TextField {...params} label="주용도" />}
    />
  )
}

PropertyType.displayName = 'PropertyType'

export default PropertyType
