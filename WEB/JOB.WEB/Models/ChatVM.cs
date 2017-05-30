using System;

namespace JOB.WEB.Models
{
    public class ChatVM
    {
        public Guid ID_SERVICO { get; set; }
        public DateTime DT_MENSAGEM { get; set; }
        public Guid ID_USUARIO { get; set; }
        public string NOME_USUARIO { get; set; }
        public string MENSAGEM { get; set; }
        public bool LIDA { get; set; }
    }
}