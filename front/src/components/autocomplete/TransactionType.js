import { fetchManagerStateList } from '@/store/slices/managerStateSlice'
import { fetchTransactionTypeList } from '@/store/slices/transactionTypeSlice'
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'

const { Autocomplete, TextField } = require('@mui/material')

const TransactionType = ({ value, onChange }) => {
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
    onChange(value ? value.transactionTypeId : '')
  }

  return (
    <Autocomplete
      value={
        options
          ? options.find((option) => option.transactionTypeId === value) || null
          : null
      }
      options={options || []}
      getOptionLabel={(options) => options.transactionTypeName || ''}
      onChange={handleChange}
      renderInput={(params) => <TextField {...params} label="거래유형" />}
    />
  )
}

TransactionType.displayName = 'TransactionType'

export default TransactionType
