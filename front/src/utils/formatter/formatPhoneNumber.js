export const formatPhoneNumber = (input) => {
  const digits = input.replace(/\D/g, '')
  const phoneNumber = `${digits.substring(0, 3)}${
    digits.length > 3 ? '-' : ''
  }${digits.substring(3, 7)}${digits.length > 7 ? '-' : ''}${digits.substring(
    7,
    11
  )}`
  return phoneNumber
}
