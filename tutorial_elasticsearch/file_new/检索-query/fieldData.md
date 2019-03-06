### fieldData
Fielddata is disabled on text fields by default.
Fielddata可以消耗大量的堆空间.加载fielddata是一个昂贵的过程，可以导致用户体验延迟命中.Fielddata在text字段上默认被禁用



在启用fielddata之前，请考虑为什么要使用text字段进行聚合，排序或在脚本中。这样做通常没有意义