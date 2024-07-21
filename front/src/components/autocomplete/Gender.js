// React, Next
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'

// Materials
import { Autocomplete, TextField } from '@mui/material'

// Custom Components

// Utils
import { fetchGenderList } from '@/store/slices/genderSlice'

const Gender = ({ value, onChange, sx, readOnly = false , label = '성별' }) => {
  const dispatch = useDispatch()
  const { options, status, error } = useSelector((state) => state.genderList) // gender state 전체를 가져옵니다

  useEffect(() => {
    if (status === 'idle') {
      dispatch(fetchGenderList())
    }
  }, [dispatch, status])

  const handleChange = (event, newValue) => {
    onChange(newValue ? newValue.name : '')
  }

  return (
    <Autocomplete
      value={
        options ? options.find((option) => option.name === value) || null : null
      }
      options={options || []}
      getOptionLabel={(option) => option.label || ''}
      onChange={handleChange}
      renderInput={(params) => <TextField {...params} label={label} />}
      disabled={readOnly}
      sx={sx}
    />
  )
}

Gender.displayName = 'Gender'

export default Gender
