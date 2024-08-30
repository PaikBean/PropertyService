// React, Next
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'

// Materials

// Custom Components

// Utils
import { fetchTransactionTypeList } from '@/store/slices/transactionTypeSlice'

const { Autocomplete, TextField } = require('@mui/material')

const TransactionType = ({ value, onChange, sx, readOnly = false }) => {
  const dispatch = useDispatch()
  const { options, status, error } = useSelector(
    (state) => state.transactionType
  )

  useEffect(() => {
    if (status === 'idle') {
      dispatch(fetchTransactionTypeList())
    }
  })

  const handleChange = (event, value) => {
    onChange(value ? value.transactionTypeName : '')
  }

  return (
    <Autocomplete
      value={
        options
          ? options.find((option) => option.transactionTypeName === value) || null
          : null
      }
      options={options || []}
      getOptionLabel={(options) => options.label || ''}
      onChange={handleChange}
      renderInput={(params) => (
        <TextField
          {...params}
          label="거래유형"
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

TransactionType.displayName = 'TransactionType'

export default TransactionType
