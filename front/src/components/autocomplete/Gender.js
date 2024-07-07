import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { Autocomplete, TextField } from '@mui/material'
import { fetchGenderList } from '@/store/slices/genderSlice'

const Gender = ({ value, onChange, label = '성별' }) => {
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
    />
  )
}

Gender.displayName = 'Gender'

export default Gender
