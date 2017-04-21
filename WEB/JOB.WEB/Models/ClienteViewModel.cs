using System;
using JOB.DATA.Domain;

namespace JOB.WEB.Models
{
    public class ClienteViewModel
    {
        public Guid ID_USUARIO { get; set; }

        public string NOME { get; set; }
        public CONTATO CONTATO { get; set; }
    }
}