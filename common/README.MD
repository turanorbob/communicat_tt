# model
[链接]:https://www.cnblogs.com/wcf6676/p/4924441.html "资料"
[图片]:https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1457704519,3529830056&fm=27&gp=0.jpg
>[链接] ![图片]
* java bean
* dto: data transfer object,
    >目前主流的DTO解决方案有定制DTO、数据传送哈希表、数据传送行集。
* po: persistant object
* vo: value object
```
public abstract class Converter < T, C > {
 
 private final Function < T,
 C > fromDto;
 private final Function < C,
 T > fromEntity;
 
 /**
  * @param fromDto
  *   Function that converts given dto entity into the domain
  *   entity.
  * @param fromEntity
  *   Function that converts given domain entity into the dto
  *   entity.
  */
 public Converter(final Function < T, C > fromDto, final Function < C, T > fromEntity) {
  this.fromDto = fromDto;
  this.fromEntity = fromEntity;
 }
 
 /**
  * @param customerDto
  *   DTO entity
  * @return The domain representation - the result of the converting function
  *   application on dto entity.
  */
 public final C convertFromDto(final T customerDto) {
  return fromDto.apply(customerDto);
 }
 
 /**
  * @param customer
  *   domain entity
  * @return The DTO representation - the result of the converting function
  *   application on domain entity.
  */
 public final T convertFromEntity(final C customer) {
  return fromEntity.apply(customer);
 }
 
 /**
  * @param dtoCustomers
  *   collection of DTO entities
  * @return List of domain representation of provided entities retrieved by
  *   mapping each of them with the conversion function
  */
 public final List < C > createFromDtos(final Collection < T > dtoCustomers) {
  return dtoCustomers.stream().map(this::convertFromDto).collect(Collectors.toList());
 }
 
 /**
  * @param customers
  *   collection of domain entities
  * @return List of domain representation of provided entities retrieved by
  *   mapping each of them with the conversion function
  */
 public final List < T > createFromEntities(final Collection < C > customers) {
  return customers.stream().map(this::convertFromEntity).collect(Collectors.toList());
 }
}
```
### 表格
|名称|描述|
|:---:|---------------|
|sybase|description|