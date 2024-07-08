import { fetchDepartmentList } from '@/store/slices/departmentSlice2'
import { Autocomplete, TextField } from '@mui/material'
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'

const Department2 = ({
  value,
  onChange,
  companyCode,
  sx,
  readOnly = false,
  label = '부서',
}) => {
  const dispatch = useDispatch()
  const { options, status, error } = useSelector(
    (state) => state.departmentList
  )

  useEffect(() => {
    dispatch(fetchDepartmentList(companyCode))
  }, [dispatch, companyCode])

  const handleClientChange = (event, value) => {
    onChange(value ? value.departmentId : '')
  }

  return (
    <Autocomplete
      value={
        options
          ? options.find((option) => option.departmentId === value) || null
          : null
      }
      options={options || []}
      getOptionLabel={(options) => options.departmentName || ''}
      onChange={handleClientChange}
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

Department2.displayName = 'Department2'

export default Department2
