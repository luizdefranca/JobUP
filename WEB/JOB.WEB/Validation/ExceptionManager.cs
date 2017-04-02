using System;
using System.Data.SqlClient;
using System.Text.RegularExpressions;

namespace JOB.WEB.Validation
{
    public static class ExceptionManager
    {
        public static string TratarMensagem(this Exception ex)
        {
            var inner = ex.InnerException;

            if (inner != null && inner.Message != null)
            {
                if (inner.InnerException is SqlException)
                {
                    var error = (SqlException)inner.InnerException;

                    switch (error.Number)
                    {
                        case 2601: //Cannot insert duplicate key row in object '%.*ls' with unique index '%.*ls'. The duplicate key value is %ls.
                            return string.Format("O item {0} já existe na base de dados e não pode ser repetido.",
                                Regex.Match(inner.InnerException.Message, @"\([0-9]+\)"));

                        case 2627: //Violation of %ls constraint '%.*ls'. Cannot insert duplicate key in object '%.*ls'. The duplicate key value is %ls.
                            return "Um item duplicado foi encontrado. Favor, tentar novamente com outra opção.";

                        default:
                            return inner.Message;
                    }
                }
                else
                {
                    return ex.Message;
                }
            }
            else
            {
                return ex.Message;
            }
        }
    }
}