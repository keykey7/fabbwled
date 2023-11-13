import * as Yup from "yup";

const numericRangeValidation = (
  fieldName: string,
  minValue: number,
  maxValue: number,
) => {
  return Yup.number()
    .typeError(`${fieldName} must be a number`)
    .integer(`${fieldName} must be a whole number`)
    .min(minValue, `${fieldName} must be at least ${minValue}`)
    .max(maxValue, `${fieldName} cannot be greater than ${maxValue}`)
    .required(`${fieldName} is required`);
};
export default Yup.object().shape({
  name: Yup.string().required("Name is required"),
  god: Yup.string().required("God is required"),
  charisma: numericRangeValidation("Charisma", 1, 6),
  combat: numericRangeValidation("Combat", 1, 6),
  magic: numericRangeValidation("Magic", 1, 6),
  sanctity: numericRangeValidation("Sanctity", 1, 6),
  scouting: numericRangeValidation("Scouting", 1, 6),
  thievery: numericRangeValidation("Thievery", 1, 6),
  description: Yup.string().required("Description is required"),
  titlesAndHonours: Yup.string().required("Titles and honours is required"),
  blessings: Yup.string().required("Blessings is required"),
});
